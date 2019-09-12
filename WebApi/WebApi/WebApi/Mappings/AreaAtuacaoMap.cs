using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class AreaAtuacaoMap : IEntityTypeConfiguration<AreaAtuacao>
    {
        public void Configure(EntityTypeBuilder<AreaAtuacao> entity)
        {
            entity.HasKey(e => e.CdAreaatuacao);

            entity.ToTable("area_atuacao");

            entity.Property(e => e.CdAreaatuacao).HasColumnName("cd_areaatuacao");

            entity.Property(e => e.DsAreaatuacao)
                .IsRequired()
                .HasColumnName("ds_areaatuacao")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");
        }
    }
}
