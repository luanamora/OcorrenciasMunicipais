using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class HistoricoOcorrenciaMap : IEntityTypeConfiguration<HistoricoOcorrencia>
    {
        public void Configure(EntityTypeBuilder<HistoricoOcorrencia> entity)
        {
            entity.HasKey(e => e.CdHistoricoocorrencia);

            entity.ToTable("historico_ocorrencia");

            entity.Property(e => e.CdHistoricoocorrencia).HasColumnName("cd_historicoocorrencia");

            entity.Property(e => e.CdOcorrencia).HasColumnName("cd_ocorrencia");

            entity.Property(e => e.DsHistoricoocorrencia)
                .IsRequired()
                .HasColumnName("ds_historicoocorrencia")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.HasOne(d => d.CdOcorrenciaNavigation)
                .WithMany(p => p.HistoricoOcorrencia)
                .HasForeignKey(d => d.CdOcorrencia)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("ocorrencia_historico_ocorrencia_fk");
        }
    }
}
